// IPMSSInterface.aidl
package com.example.frand01_advandfinalth_giangnnh;
import com.example.frand01_advandfinalth_giangnnh.data.model.TireDefault;
import com.example.frand01_advandfinalth_giangnnh.data.model.TireSensor;

interface IPMSSInterface {


  TireDefault getTireDefaut();
  long insertTireDefault(inout  TireDefault  tireDefault);
  int updateTireDefault(inout  TireDefault  tireDefault);
  int deleteTireDefault(inout TireDefault  tireDefault);

  TireSensor getTireSensor();
  long insertTireSensor(inout  TireSensor  tireSensor);
  int updateTireSensor(inout  TireSensor  tireSensor);
  int deleteTireSensor(inout TireSensor  tireSensor);
}