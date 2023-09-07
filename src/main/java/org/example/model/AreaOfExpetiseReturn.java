package org.example.model;

import org.example.entity.AreaOfExpertise;

public class AreaOfExpetiseReturn {
    private Long id;

    private String code;

    private String area;


    public AreaOfExpetiseReturn(){}

    public AreaOfExpetiseReturn(Long id, String code, String area) {
        this.id = id;
        this.code = code;
        this.area = area;
    }

    public AreaOfExpetiseReturn(AreaOfExpertise areaOfExpertise){
        this.id = areaOfExpertise.getId();
        this.code = areaOfExpertise.getCode();
        this.area = areaOfExpertise.getArea();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
