//package com.waihai.usercenter.service;
//
//import com.waihai.usercenter.model.domin.User;
//import jakarta.annotation.Resource;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.StopWatch;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
//
//@SpringBootTest
//public class InsertUserTest {
//
//    @Resource
//    private UserService userService;
//
//    //线程设置
//    private ExecutorService executorService = new ThreadPoolExecutor(16, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));
//    /**
//     * 循环插入用户  耗时：7260ms
//     * 批量插入用户   1000  耗时： 4751ms
//     */
//    @Test
//    public void doInsertUser() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 1000;
//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i < INSERT_NUM; i++) {
//            User user = new User();
//            user.setUsername("假外害");
//            user.setUserAccount("waihai");
//            user.setAvatarUrl("https://ts1.cn.mm.bing.net/th/id/R-C.e4fe1a1677f72143432de42d44e85f9f?rik=QNhDoEtIS2Ve3A&riu=http%3a%2f%2finews.gtimg.com%2fnewsapp_match%2f0%2f15103659616%2f0&ehk=gvcKqCFPB5ADv7JtilqqKL%2b1U87peA41C0HCNdeflKc%3d&risl=&pid=ImgRaw&r=0");
//            user.setProfile("二十岁的草木无可奈何，等待着三十五岁的颂歌。");
//            user.setGender(0);
//            user.setUserPassword("12345678");
//            user.setPhone("123456789108");
//            user.setEmail("waihai@qq.com");
//            user.setUserStatus(0);
//            user.setUserRole(0);
//            user.setPlanetCode("666");
//            user.setTags("[]");
//            userList.add(user);
//        }
//        userService.saveBatch(userList,100);
//        stopWatch.stop();
//        System.out.println( stopWatch.getLastTaskTimeMillis());
//
//    }
//
//    /**
//     * 并发批量插入用户   100000  耗时： 26830ms
//     */
//    @Test
//    public void doConcurrencyInsertUser() {
//        int n = 3;
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 10000;
//        // 分十组
//        int j = 0;
//        //批量插入数据的大小
//        int batchSize = 500;
//        List<CompletableFuture<Void>> futureList = new ArrayList<>();
//        // i 要根据数据量和插入批量来计算需要循环的次数。（鱼皮这里直接取了个值，会有问题,我这里随便写的）
//        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
//
//            List<User> userList = new ArrayList<>();
//            while (true){
//                n++;
//                j++;
//                User user = new User();
//                user.setUsername("假外害");
//                user.setUserAccount("waihai" + n);
//                user.setAvatarUrl("https://s21.ax1x.com/2024/06/16/pkwXGpF.jpg");
//                user.setProfile("二十六的草木无可奈何，等待着三十五岁的颂歌。");
//                user.setGender(0);
//                user.setUserPassword("ce42fcb61c022204c896125e29877c61");
//                user.setPhone("123456789108");
//                user.setEmail("waihai@qq.com");
//                user.setUserStatus(0);
//                user.setUserRole(0);
//                user.setPlanetCode("666");
//                user.setTags("[]");
//                userList.add(user);
//                if (j % batchSize == 0 ){
//                    break;
//                }
//            }
//            //异步执行
//            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
//                System.out.println("ThreadName：" + Thread.currentThread().getName());
//                userService.saveBatch(userList,batchSize);
//            },executorService);
//            futureList.add(future);
//        }
//        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
//
//        stopWatch.stop();
//        System.out.println( stopWatch.getLastTaskTimeMillis());
//
//    }
//}
