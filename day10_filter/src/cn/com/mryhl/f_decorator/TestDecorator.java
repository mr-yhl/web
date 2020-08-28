package cn.com.mryhl.f_decorator;

public class TestDecorator {

    public static void main(String[] args) {

        // 多态（面向接口）
        Phone phone = new Lvjing(new Meiyan(new Huawei()));
        phone.take();
        System.out.println("-------------");
        phone.call();
    }
}

// Phone接口
interface Phone {

    // 拍照
    void take();

    // 打电话
    void call();
}

// 华为厂商实现类
class Huawei implements Phone {

    @Override
    public void take() {
        System.out.println("3200W高清摄像");
    }

    @Override
    public void call() {
        System.out.println("5G视频通话");
    }
}

// 抽取PhoneWrapper包装类

class PhoneWrapper implements Phone {

    private Phone phone;

    public PhoneWrapper(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void take() {
        this.phone.take();
    }

    @Override
    public void call() {
        this.phone.take();
    }
}

// 美颜增强
class Meiyan extends PhoneWrapper {

    public Meiyan(Phone phone) {
        super(phone);
    }


    // 对拍照增强
    @Override
    public void take() {
        super.take();
        System.out.println("磨皮、瘦脸、大眼、美白");
    }
}

// 滤镜增强
class Lvjing implements Phone {

    private Phone phone;

    public Lvjing(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void take() {
        this.phone.take();
        System.out.println("对背景虚化...");
    }

    @Override
    public void call() {
        this.phone.call();
    }
}
