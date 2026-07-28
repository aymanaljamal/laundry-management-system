package com.ayman.laundry.security.util;


import com.ayman.laundry.security.service.CustomUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



public final class SecurityUtils {


    private SecurityUtils(){}



    public static CustomUserDetails getCurrentUser(){


        Authentication authentication =

                SecurityContextHolder
                        .getContext()
                        .getAuthentication();



        if(
                authentication == null
                ||
                !authentication.isAuthenticated()
        ){

            return null;

        }



        return (CustomUserDetails)
                authentication.getPrincipal();


    }



    public static Long getCurrentUserId(){

        return getCurrentUser()
                .getUserId();

    }



    public static String getCurrentUsername(){

        return getCurrentUser()
                .getUsername();

    }



}