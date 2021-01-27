package aaa;

class AAAPackagePrivateClass {
    public String publicField;
    protected String protectedField;
    String packagePrivateField;
    private String privateField;

    public void publicMethod() {
        System.out.println("publicMethod() is called");
    }

    protected void protectedMethod() {
        System.out.println("protectedMethod() is called");
    }

    void packagePrivateMethod() {
        System.out.println("packagePrivateMethod() is called");
    }

    private void privateMethod() {
        System.out.println("privateMethod() is called");
    }
}
