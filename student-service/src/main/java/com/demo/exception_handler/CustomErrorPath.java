package com.demo.exception_handler;

public class CustomErrorPath {
    private String className; 
    private String methodName; 
    private String fileName; 
    private int lineNumber;

    public CustomErrorPath() {
    }

    public CustomErrorPath(String className, String methodName, String fileName, int lineNumber) {
        this.className = className;
        this.methodName = methodName;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }    

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public int getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "CustomErrorPath [className=" + className + ", methodName=" + methodName + ", fileName=" + fileName
                + ", lineNumber=" + lineNumber + "]";
    } 

    
}
