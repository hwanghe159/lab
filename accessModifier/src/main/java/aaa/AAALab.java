package aaa;

import bbb.BBBPackagePrivateClass;
import bbb.BBBPublicClass;

public class AAALab {
    public void test() {
        AAAPublicClass aaaPublicClass = new AAAPublicClass();
        System.out.println(aaaPublicClass.publicField);
        System.out.println(aaaPublicClass.protectedField);
        System.out.println(aaaPublicClass.packagePrivateField);
        System.out.println(aaaPublicClass.privateField);
        aaaPublicClass.publicMethod();
        aaaPublicClass.protectedMethod();
        aaaPublicClass.packagePrivateMethod();
        aaaPublicClass.privateMethod();

        AAAPackagePrivateClass aaaPackagePrivateClass = new AAAPackagePrivateClass();
        System.out.println(aaaPackagePrivateClass.publicField);
        System.out.println(aaaPackagePrivateClass.protectedField);
        System.out.println(aaaPackagePrivateClass.packagePrivateField);
        System.out.println(aaaPackagePrivateClass.privateField);
        aaaPackagePrivateClass.publicMethod();
        aaaPackagePrivateClass.protectedMethod();
        aaaPackagePrivateClass.packagePrivateMethod();
        aaaPackagePrivateClass.privateMethod();

        BBBPublicClass bbbPublicClass = new BBBPublicClass();
        System.out.println(bbbPublicClass.publicField);
        System.out.println(bbbPublicClass.protectedField);
        System.out.println(bbbPublicClass.packagePrivateField);
        System.out.println(bbbPublicClass.privateField);
        bbbPublicClass.publicMethod();
        bbbPublicClass.protectedMethod();
        bbbPublicClass.packagePrivateMethod();
        bbbPublicClass.privateMethod();

        BBBPackagePrivateClass bbbPackagePrivateClass = new BBBPackagePrivateClass();
    }
}
