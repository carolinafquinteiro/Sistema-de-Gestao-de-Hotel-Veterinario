package hva.employee;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * Represents an employee in the hotel application
 */
public  class Employee implements Serializable{
    // Employee attributes
    private String id;
    private String name;
    private String type;

    /**
     * Constructor to initialize the attributes of the Employee
     *
     * @param id    the identifier of the employee
     * @param name  the name of the employee
     * @param type  the type of the employee (e.g., Vet, TRT)
     */
    public Employee(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Getters to return the employee's data
     *
     * @return the identifier, name, and type of the employee
     */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }
    
}
