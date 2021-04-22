package com.example.demo.rest;


import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department)
    {
        return new ResponseEntity<Department>(departmentService.createDepartment(department),HttpStatus.CREATED);
    }
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> displayAll()
    {
        return new ResponseEntity<List<Department>>(departmentService.displayAllDepartment(),HttpStatus.OK);
    }

    @GetMapping(value= "/departmentById/{department-id}")
    public Optional<Department> getById(@PathVariable(value= "department-id") String id) {
        return departmentService.findDepartmentById(id);
    }
@GetMapping(value = "/department/employee/{employeeName}")
public ResponseEntity<Department> getDepartmentByName(@PathVariable(value="employeeName") String name){
        return  ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartmentByEmployeeName(name));
}

    @PutMapping(value= "/update/{department-id}")
    public String update(@PathVariable(value= "department-id") Integer id, @RequestBody Department d) {
       d.setDepartmentId(id);
        departmentService.updateDepartment(d);
        return "Employee record for employee-id= " + id + " updated.";
    }


    @DeleteMapping(value= "/delete/{department-id}")
    public String delete(@PathVariable(value= "department-id") String id) {
        departmentService.deleteDepartmentById(id);
        return "Employee record for employee-id= " + id + " deleted.";
    }


    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {
        departmentService.deleteAllDepartments();
        return "All employee records deleted.";
    }
}
