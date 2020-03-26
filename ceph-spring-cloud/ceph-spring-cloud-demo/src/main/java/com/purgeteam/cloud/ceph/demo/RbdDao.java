package com.purgeteam.cloud.ceph.demo;

import com.ceph.rados.IoCTX;
import com.ceph.rados.Rados;
import com.ceph.rados.exceptions.RadosException;
import com.ceph.rbd.Rbd;
import com.ceph.rbd.RbdException;
import com.ceph.rbd.RbdImage;
import com.ceph.rbd.jna.RbdImageInfo;
import com.ceph.rbd.jna.RbdSnapInfo;

import java.util.Arrays;
import java.util.List;

public class RbdDao {

    private static Rados rados;
    private static IoCTX ioctx;
    private static Rbd rbd;
    /**
     * 连接上ceph环境
     */
    public static void connectCeph(){
        try {
            //利用参数连接Ceph
            rados = new Rados("liuyz");
            rados.confSet("mon_host", "10.16.138.26");
            rados.confSet("key", "AdHJ4sPQhr1JL6ucjOdqhVevtP7fw7DtkXkXVQPN");
            rados.connect();
            ioctx = rados.ioCtxCreate("rbd");
            rbd = new Rbd(ioctx);
            System.out.println("successs connetc");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    /**
     * 返回所有的image,并展示其详细信息
     * @return
     */
    public static List<String> imageList(){
        List<String> imageList=null;
        try {
            imageList = Arrays.asList(rbd.list());
            for(String s:imageList){
                showDetailOfImage(s);
            }
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imageList;
    }

    /**
     * 显示image的详细信息
     * @param imageName
     */
    public static void showDetailOfImage(String imageName){
        RbdImage image;
        try {
            image = rbd.open(imageName);
            RbdImageInfo info = image.stat();
            System.out.println("=================================================================");
            System.out.println("imageName:    "+imageName);
            System.out.println("imageSize:    "+info.size);
            System.out.println("order:   "+info.order);
            rbd.close(image);
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 以格式1创建image
     * @param imageName 名称
     * @param imageSize　大小
     */
    public static void createRbd_format1(String imageName, long imageSize){
        try {
            rbd.create(imageName, imageSize);
            RbdImage image = rbd.open(imageName);
            boolean oldFormat = image.isOldFormat();
            System.out.println("imageFormat:==========================="+oldFormat);

            rbd.close(image);
        } catch (RbdException e) {
            System.out.println(e.getMessage() + ": " + e.getReturnValue());
        }
    }

    /**
     * 以格式2创建image，ceph 仅支持克隆 format 2 映像（即用 rbd create –format 2 创建的），而且内核 rbd 模块还不支持。
     　　所以现在你 只能用 QEMU/KVM 或 librbd直接访问克隆品
     * @param imageName 名称
     * @param imageSize　大小
     */
    public static void createRbd_format2(String imageName, long imageSize){
        try {
            int features = (1<<0);
            System.out.println("features=============="+features);
            rbd.create(imageName, imageSize,features, 0);
            RbdImage image = rbd.open(imageName);
            boolean oldFormat = image.isOldFormat();
            System.out.println("imageFormat:==========================="+oldFormat);
            rbd.close(image);
            image.flatten();
        } catch (RbdException e) {
            System.out.println(e.getMessage() + ": " + e.getReturnValue());
        }
    }

    /**
     * 方法创建一个image并对重设置大小为初始化大小的2倍
     * @param imageName
     */
    public static void resizeImage(String imageName){
        long initialSize = 10485760;
        long newSize = initialSize * 2;
        try {
            int features = (1<<0);
            System.out.println("features=============="+features);
            rbd.create(imageName, initialSize,features, 0);
            RbdImage image = rbd.open(imageName);
            image.resize(newSize);
            rbd.close(image);
        } catch (RbdException e) {
            System.out.println(e.getMessage() + ": " + e.getReturnValue());
        }
    }

    /**
     * 创建映像的快照
     * @param imageName 映像名称
     * @param snapName 快照名称
     */
    public static void createSnap(String imageName,String snapName){
        try {
            RbdImage image = rbd.open(imageName);
            //创建快照
            image.snapCreate(snapName);
            //保护快照可以防止快照被删除
            image.snapProtect(snapName);
            //返回一个image的所有快照
            List<RbdSnapInfo> snaps = image.snapList();
            for(RbdSnapInfo rbds:snaps){
                System.out.println("快照名称："+rbds.name);
                System.out.println("快照大小："+rbds.size);
            }
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 通过快照克隆出新的image
     * @param parentImageName 快照对应的image名称
     * @param snapName 快照的名称
     * @param newImageName 生成的新的image的名称
     */
    public static void copySnapToNewImage(String parentImageName,String snapName,String newImageName){
        int features = (1<<0);
        try {
            rbd.clone(parentImageName, snapName, ioctx, newImageName, features, 0);
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 删除某个image的名叫 snapName的快照,需要注意的是要删除快照，必须保证快照没有copy的子image，否则会删除失败。
     * @param imageName
     * @param snapName
     */
    public static void deleteSnap(String imageName,String snapName){
        try {
            RbdImage image = rbd.open(imageName);
            image.snapUnprotect(snapName);
            image.snapRemove(snapName);
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 删除某一个image
     * @param r
     * @param io
     * @param imageName
     * @throws RadosException
     * @throws RbdException
     */
    public static void cleanupImage(Rados r, IoCTX io, String imageName) {
        try {
            if (r != null) {
                if (io != null) {
                    Rbd rbd = new Rbd(ioctx);
                    RbdImage image = rbd.open(imageName);
                    rbd.close(image);
                    rbd.remove(imageName);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public static void main(String[] args){
        connectCeph();
        //createRbd_format1("mysql-hzb-2",10737418240l);
        //createRbd_format2("imageformat2",10485760);
        //cleanupImage(rados,ioctx,"mysql-hzb");
        //resizeImage("mysql-hzb");

        // createSnap("imageformat3","imageformat3-snap");
        //copySnapToNewImage("imageformat3","imageformat3-snap","imageformat3-copy");
        //deleteSnap("imageformat3","imageformat3-snap");
        imageList();
    }
}
