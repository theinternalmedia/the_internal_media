package com.tim.model;

public class TeacherModel extends UserModel {

    /**
     * thinhnguyen
     */

    private static final long serialVersionUID = 3200306173932990958L;

    private boolean isHeadOfFaculty;

    private boolean isManager = false;

    public boolean isHeadOfFaculty() {
        return isHeadOfFaculty;
    }

    public void setHeadOfFaculty(boolean headOfFaculty) {
        isHeadOfFaculty = headOfFaculty;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
