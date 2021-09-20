// IServerService.aidl
package com.example.connectorlibrary.controller;

import com.example.connectorlibrary.enitity.User;
import com.example.connectorlibrary.controller.IServerServiceCallback;
import com.example.connectorlibrary.enitity.Health;
// Declare any non-default types here with import statements

/**
 * Example of defining an interface for calling on to a remote service
 * (running in another process).
 *
 * @author Nguyen Ngoc Ha Giang
 * @version 1.0, 10/09/2021
 * @since 1.0
 */
interface IServerService {
/**
    * Often you want to allow a service to call back to its clients.
    * This shows how to do so, by registering a callback interface with
    * the service.
    */
    void registerCallback(IServerServiceCallback callback);


/**
    * Remove a previously registered callback interface.
    */
    void unregisterCallback(IServerServiceCallback callback);
/**
    * Called upon user signup request process.
    */
    void userSignUp(in User user);
/**
    * Called upon user signin request process.
    */
    void userSignIn(in String phone_number);
  /**
      * Called upon user health request process.
      */
    void insertHealth(in Health health);
 /**
       * Called upon get user health request process.
     */
    void getUserHealths();
 /**
        * Called upon get user request process.
      */
     void getUserInformation(in int user_id);
 /**
         * Called upon get user request process.
       */
      void getAllUsers();
  /**
          * Called upon update user request process.
        */
     void updateUser(in User user);

      /**
               * Called upon delete user request process.
             */
     void deleteUser(in User user);
      /**
               * Called upon update user request process.
             */
     void lockUser(in User user);
/**
      * Called upon get static request process.
   */
    void getStatus();

/**
          * Called upon get symptom request process.
     */

    void getSymptom();
     /**
               * Called upon get gender request process.
          */
    void getGender();
       /**
                   * Called upon get history covid vietname request process.
              */
     void getHistoryCovidVn();
        /**
                        * Called upon get history covid world request process.
                   */
     void getHistoryCovidWorld();
}