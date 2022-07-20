package com.example.alumnis;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Alumni_Data implements Serializable {

    @Exclude
    private String id;
    public String ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_CONTACT_NO,ALUMNI_EMAIL,ALUMNI_JOB_STATUS,ALUMNI_COMPANY_NAME,ALUMNI_DESIGNATION,ALUMNI_COMPANY_ADDRESS,ALUMNI_UG_PROJECT,ALUMNI_SOCIAL_PROFILE;
    public Alumni_Data() {
    }

    public Alumni_Data(String ALUMNI_NAME, String ALUMNI_PASSING_YEAR, String ALUMNI_CONTACT_NO, String ALUMNI_EMAIL, String ALUMNI_JOB_STATUS, String ALUMNI_COMPANY_NAME, String ALUMNI_DESIGNATION, String ALUMNI_COMPANY_ADDRESS, String ALUMNI_UG_PROJECT, String ALUMNI_SOCIAL_PROFILE) {
        this.ALUMNI_NAME = ALUMNI_NAME;
        this.ALUMNI_PASSING_YEAR = ALUMNI_PASSING_YEAR;
        this.ALUMNI_CONTACT_NO = ALUMNI_CONTACT_NO;
        this.ALUMNI_EMAIL = ALUMNI_EMAIL;
        this.ALUMNI_JOB_STATUS = ALUMNI_JOB_STATUS;
        this.ALUMNI_COMPANY_NAME = ALUMNI_COMPANY_NAME;
        this.ALUMNI_DESIGNATION = ALUMNI_DESIGNATION;
        this.ALUMNI_COMPANY_ADDRESS = ALUMNI_COMPANY_ADDRESS;
        this.ALUMNI_UG_PROJECT = ALUMNI_UG_PROJECT;
        this.ALUMNI_SOCIAL_PROFILE = ALUMNI_SOCIAL_PROFILE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//
//    public String getALUMNI_NAME() {
//        return ALUMNI_NAME;
//    }
//
//    public String getALUMNI_PASSING_YEAR() {
//        return ALUMNI_PASSING_YEAR;
//    }
//
//    public String getALUMNI_CONTACT_NO() {
//        return ALUMNI_CONTACT_NO;
//    }
//
//    public String getALUMNI_EMAIL() {
//        return ALUMNI_EMAIL;
//    }
//
//    public String getALUMNI_JOB_STATUS() {
//        return ALUMNI_JOB_STATUS;
//    }
//
//    public String getALUMNI_COMPANY_NAME() {
//        return ALUMNI_COMPANY_NAME;
//    }
//
//    public String getALUMNI_DESIGNATION() {
//        return ALUMNI_DESIGNATION;
//    }
//
//    public String getALUMNI_COMPANY_ADDRESS() {
//        return ALUMNI_COMPANY_ADDRESS;
//    }
//
//    public String getALUMNI_ALUMNI_UG_PROJECT() {
//        return ALUMNI_UG_PROJECT;
//    }
//
//    public String getALUMNI_SOCIAL_PROFILE() {
//        return ALUMNI_SOCIAL_PROFILE;
//    }
}
