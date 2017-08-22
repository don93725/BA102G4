package com.members.model;

import com.coaches.model.CoachesVO;
import com.students.model.StudentsVO;

/**
 * Created by cuser on 2017/8/9.
 */

public class NewMembers {
    StudentsVO studentsVO;
    CoachesVO coachesVO;
    MembersVO membersVO;

    public NewMembers(){}

    public StudentsVO getStudentsVO() {
        return studentsVO;
    }

    public void setStudentsVO(StudentsVO studentsVO) {
        this.studentsVO = studentsVO;
    }

    public CoachesVO getCoachesVO() {
        return coachesVO;
    }

    public void setCoachesVO(CoachesVO coachesVO) {
        this.coachesVO = coachesVO;
    }

    public MembersVO getMembersVO(){ return membersVO;}

    public void setMembersVO(MembersVO membersVO) { this.membersVO = membersVO;}

    public NewMembers(StudentsVO studentsVO, CoachesVO coachesVO, MembersVO membersVO) {
        this.studentsVO = studentsVO;
        this.coachesVO = coachesVO;
        this.membersVO = membersVO;
    }
}
