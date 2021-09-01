// IStudentBinder.aidl
package com.example.serveraidl;
import com.example.serveraidl.data.model.Student;
// Declare any non-default types here with import statements

interface IStudentBinder {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
        List<Student> getAllStudents();

      long insertStudent(inout Student student);

      int updateStudent(inout Student student);

      int deleteStudent(inout Student student);
}