package com.cmq.bo.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DoctorConfigurationResponseBO implements Serializable {
    private static final long serialVersionUID = -6137108058248102787L;

    private Integer id;

    @Deprecated
    private String avatar;

    private String name;

    private String sex;

    private String idCardNumber;

    private String mobile;

    private String hospitalName;

    private String memo;

    private boolean responsible;

    @Deprecated
    private Integer isResponsible;

    private List<Integer> districtIds;

    private List<Integer> functionIds;

    //selector fields
    //selected values
    //first => province, and then one by one
    private Integer first;

    private Integer second;

    private Integer third;

    private Integer fourth;

    private List<Integer> fifth = new ArrayList<>();

    private List<DistrictSelectorResponseBO> provinces = new ArrayList<>();

    private List<DistrictSelectorResponseBO> cities = new ArrayList<>();

    private List<DistrictSelectorResponseBO> areaes = new ArrayList<>();

    private List<DistrictSelectorResponseBO> towns = new ArrayList<>();

    private List<DistrictSelectorResponseBO> villages = new ArrayList<>();

    private boolean isCityShow = false;

    private boolean isAreaShow = false;

    private boolean isTownShow = false;

    private boolean isVillageShow = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isResponsible() {
        return responsible;
    }

    public void setResponsible(boolean responsible) {
        this.responsible = responsible;
    }

    public Integer getIsResponsible() {
        return isResponsible;
    }

    public void setIsResponsible(Integer isResponsible) {
        this.isResponsible = isResponsible;
    }

    public List<Integer> getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(List<Integer> districtIds) {
        this.districtIds = districtIds;
    }

    public List<Integer> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(List<Integer> functionIds) {
        this.functionIds = functionIds;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

    public Integer getFourth() {
        return fourth;
    }

    public void setFourth(Integer fourth) {
        this.fourth = fourth;
    }

    public List<Integer> getFifth() {
        return fifth;
    }

    public void setFifth(List<Integer> fifth) {
        this.fifth = fifth;
    }

    public List<DistrictSelectorResponseBO> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<DistrictSelectorResponseBO> provinces) {
        this.provinces = provinces;
    }

    public List<DistrictSelectorResponseBO> getCities() {
        return cities;
    }

    public void setCities(List<DistrictSelectorResponseBO> cities) {
        this.cities = cities;
    }

    public List<DistrictSelectorResponseBO> getAreaes() {
        return areaes;
    }

    public void setAreaes(List<DistrictSelectorResponseBO> areaes) {
        this.areaes = areaes;
    }

    public List<DistrictSelectorResponseBO> getTowns() {
        return towns;
    }

    public void setTowns(List<DistrictSelectorResponseBO> towns) {
        this.towns = towns;
    }

    public List<DistrictSelectorResponseBO> getVillages() {
        return villages;
    }

    public void setVillages(List<DistrictSelectorResponseBO> villages) {
        this.villages = villages;
    }

    public boolean isCityShow() {
        return isCityShow;
    }

    public void setCityShow(boolean cityShow) {
        isCityShow = cityShow;
    }

    public boolean isAreaShow() {
        return isAreaShow;
    }

    public void setAreaShow(boolean areaShow) {
        isAreaShow = areaShow;
    }

    public boolean isTownShow() {
        return isTownShow;
    }

    public void setTownShow(boolean townShow) {
        isTownShow = townShow;
    }

    public boolean isVillageShow() {
        return isVillageShow;
    }

    public void setVillageShow(boolean villageShow) {
        isVillageShow = villageShow;
    }
}
