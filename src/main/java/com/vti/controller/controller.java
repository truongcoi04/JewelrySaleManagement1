package com.vti.controller;

import com.vti.entity.Employee;
import com.vti.entity.GetDetailShareRevenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/Employee")
public class controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public Employee getForObject() {
        int id = 1;
        return restTemplate.getForObject("/employee/" + id, Employee.class);
    }


    @PostMapping("/post-for-object")
    public Employee postForObject() {
        Employee employee = new Employee();
        return restTemplate.postForObject("/", employee, Employee.class);
    }

    @PostMapping("/post-for-entity")
    public Employee postForLo() {
        Employee employee = new Employee();
        employee.setName("Deftweweewewe");
        HttpEntity<Employee> entity = restTemplate.postForEntity("/", employee, Employee.class);
        return entity.getBody();
    }

    @GetMapping("/get-ex")
    public GetDetailShareRevenue getForObjectGetDetailShareRevenue() {
        int id = 1;
        return restTemplate.getForObject("/getDetailShareRevenue/" + id, GetDetailShareRevenue.class);
    }

    @PutMapping("/put-ex/{id}")
    public GetDetailShareRevenue putExchange(@PathVariable(name = "id") Long id) {
        GetDetailShareRevenue getDetailShareRevenue = new GetDetailShareRevenue();
        getDetailShareRevenue.setShare_trans_id(id);
//        getDetailShareRevenue.setService_name("sdsdsdsdsdsdas");
        getDetailShareRevenue.setContract_no("1234567890");
//        employee.setId(id);
//        employee.setFirstName("Deft");
//        employee.setLastName("blog");
//        employee.setYearlyIncome(2021);
        return restTemplate.exchange( "/getDetailShareRevenue/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(getDetailShareRevenue),
                GetDetailShareRevenue.class,
                Long.toString(id)).getBody();
    }

    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject("https://6302de819eb72a839d74d920.mockapi.io/employee", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }

    public static void main(String[] args) {
        listAllUsers();
    }

}
