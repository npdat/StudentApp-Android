package com.example.dc2dev.studentapp.data.clients.service;

import android.content.Context;

import com.example.dc2dev.studentapp.data.clients.database.TableMember;
import com.example.dc2dev.studentapp.domain.entities.service.MemberService;

/**
 * Created by npdat on 6/14/2017.
 */

public class MemberDataService implements MemberService {
    TableMember tableMember;

    public MemberDataService(Context context) {
        tableMember = new TableMember(context);
    }

    @Override
    public boolean login(String email, String password) {
        boolean isLogin = tableMember.isExist(email, password);
        return isLogin;
    }
}
