package edu.utn.Controller;


import edu.utn.Dto.LoginResponseDto;
import edu.utn.Exceptions.NoTokenException;
import edu.utn.Exceptions.UserNotExistsException;
import edu.utn.Model.Bill;
import edu.utn.Utils.ProjetUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bill")
@Api(tags = "Bill Controller")
public class BillController {



    final Map<Integer, List<Bill>> bills;
    final LoginController loginController;

    @Autowired
    public BillController(LoginController loginController) {
        this.loginController = loginController;
        this.bills = ProjetUtils.generateBillsForUsers();
    }

    @CrossOrigin("*")
    @GetMapping("/")
    @ApiOperation(value = "List of bills of client", notes = "Return the list of bills from the client filtered by the request param, if none then all found")

    public ResponseEntity<List<Bill>> getAll(@RequestBody(required = false) LoginResponseDto autorization, @RequestParam(required = false) LocalDate from, @RequestParam(required = false) LocalDate to) throws NoTokenException, UserNotExistsException {

        if (autorization == null){
            autorization = new LoginResponseDto("1");
        }

        if (autorization.getAutorization() == null) {
            throw new NoTokenException();
        }

        if (!loginController.userExists(Integer.parseInt(autorization.getAutorization()))){
            throw new UserNotExistsException();
        }

        List<Bill> billsOfClient = this.bills.get(Integer.parseInt(autorization.getAutorization()));

        if (billsOfClient == null || billsOfClient.size() == 0) {
            return ResponseEntity.noContent().build();
        }


        if (from == null && to == null) {


            return ResponseEntity.ok(billsOfClient);

        } else {
            List<Bill> filteredBills = filterByDates(billsOfClient, from, to);

            if (filteredBills.size() == 0) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(filteredBills);
        }


    }

    @CrossOrigin("*")
    @GetMapping
    public ResponseEntity<List<Bill>> getAll(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate to){
        System.out.println("From: "+ from+" to: "+to);
        List<Bill> billsOfClient = this.bills.get(1);
        if (from != null && to != null)
        {

            billsOfClient =  this.filterByDates(billsOfClient, from,to);

        }
        return ResponseEntity.ok(billsOfClient);
    }





    private List<Bill> filterByDates(List<Bill> billsOfClient, LocalDate from, LocalDate to) {
        List<Bill> billsToReturn = new ArrayList<>();
        for (Bill b : billsOfClient) {
            if (b.getDate().isAfter(from.minusDays(1)) && b.getDate().isBefore(to.plusDays(1))) {
                billsToReturn.add(b);
            }
        }
        return billsToReturn;
    }


}
