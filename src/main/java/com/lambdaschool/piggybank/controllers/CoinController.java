package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CoinController
{
    @Autowired
    private CoinRepository coinrepos;

    private List<Coin> findCoin(List<Coin> myList, CheckCoin tester)
    {
        List<Coin> tempList = new ArrayList<>();

        for (Coin c : myList)
        {
            if (tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }
    // http://localhost:2019/total
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> displayTotal()
    {
        List<Coin> myList = new ArrayList<>();

        coinrepos.findAll().iterator().forEachRemaining(myList::add);

        double total = 0.00;
        for (Coin c : myList)
        {

            if (c.getQuantity() == 1)
        {
            System.out.println(c.getQuantity() + " " + c.getName());
            total = total + c.getValue() * c.getQuantity();
        } else
            {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
                total = total + c.getValue() * c.getQuantity();
            }
        }
        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
