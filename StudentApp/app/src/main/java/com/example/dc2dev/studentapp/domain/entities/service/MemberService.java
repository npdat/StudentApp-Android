package com.example.dc2dev.studentapp.domain.entities.service;

import com.example.dc2dev.studentapp.domain.entities.Member;

/**
 * Created by npdat on 6/14/2017.
 */

public interface MemberService {
    boolean login(String email, String password);
    boolean signup(Member mb);
}
