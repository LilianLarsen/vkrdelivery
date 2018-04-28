package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
public class Hello {
    @Autowired
    PersonRepository rep;
    @Autowired
    CourierRepository courier_rep;
    @Autowired
    OrdRepository order_rep;

    @GetMapping(path = "/GetPersonTemplate")
    public Person GetPersonTemplate () {
        Person person = new Person();
        return (person);
    }

   @PostMapping(path = "/RegistrationStart")
    public String StartOfRegistration (@RequestBody Person request){
        String log = request.getLogin();
        String pass = request.getPassword();
        String em = request.getEmail();

       Iterable<Person> persons = rep.findAll();
        for (Person man : persons)
        {
            String password = man.getPassword();
            String login = man.getLogin();
            String email = man.getEmail();
            if ((password.equals(pass)) || (login.equals(log)) || (email.equals(em)))
                return("Ошибка: данный логин, пароль или email уже существует");
        }

       int min = 100000; // Минимальное число для диапазона
       int max = 1000000; // Максимальное число для диапазона
       int checkCode = min + (int)(Math.random() * ((max - min)));
       String checkStr = Integer.toString(checkCode);

       final String username = "noreply.checkcode@gmail.com";
       final String password = "nstuavt4092018";

       Properties props = new Properties();
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");

       Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(username, password);
                   }
               });

       try {

           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress("noreply.checkcode@gmail.com"));
           message.setRecipients(Message.RecipientType.TO,
                   InternetAddress.parse(em));
           message.setSubject("Подтверждение регистрации");
           message.setText("Код подтверждения: "
                   + checkCode);

           Transport.send(message);

           System.out.println("Done");

       } catch (MessagingException e) {
           throw new RuntimeException(e);
       }

       return(checkStr);
    }

    @PostMapping(path = "/RegistrationEnd")
    public String EndOfRegistration (@RequestBody Person request){
        request.setIs_courier(false);
        rep.save(request);
        return("Регистрация прошла успешно");
    }

    @PostMapping(path = "/AutorisationAsClient")
    public Person AutorisationAsClient (@RequestBody Person request){
        String log = request.getLogin();
        String pass = request.getPassword();

        Person enter_person = rep.findByLogin(log);
        if (enter_person == null)
            return request;

        String enter_pass = enter_person.getPassword();

        if (enter_pass.equals(pass))
            return enter_person;
        else return request;
    }

    @PostMapping(path = "/AutorisationAsCourier")
    public Courier AutorisationAsCourier (@RequestBody Person request) {
        String log = request.getLogin();
        String pass = request.getPassword();

        Courier new_courier = new Courier();

        Person enter_person = rep.findByLogin(log);
        if (enter_person == null)
            return new_courier;

        String enter_pass = enter_person.getPassword();
        Optional<Courier> optionalCourier = courier_rep.findById(Long.toString(enter_person.getPid()));

        if (enter_pass.equals(pass))
            if (enter_person.getIs_courier() == true)
                return (optionalCourier.orElse(new_courier));
            else return new_courier;
        else return new_courier;
    }

    @PostMapping(path = "/ForgetPasswordStart")
    public String ForgetPasswordStart (@RequestBody String email) {
        int min = 100000; // Минимальное число для диапазона
        int max = 1000000; // Максимальное число для диапазона
        int checkCode = min + (int)(Math.random() * ((max - min)));
        String checkStr = Integer.toString(checkCode);

        final String username = "noreply.checkcode@gmail.com";
        final String password = "nstuavt4092018";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply.checkcode@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Подтверждение смены пароля");
            message.setText("Код подтверждения: "
                    + checkCode);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return(checkStr);
    }

    @PostMapping(path = "/ForgetPasswordEnd")
    public void ForgetPasswordEnd (@RequestBody String in) {
        String[] empass = in.split(",");
        Person new_pass = rep.findByEmail(empass[0]);
        new_pass.setPassword(empass[1]);
        rep.save(new_pass);
    }

    @PostMapping(path = "/ClientProfile")
    public void ClientProfile (@RequestBody Person request){
        Person enter_person = rep.findByLogin(request.getLogin());
        if (enter_person == null)
            return;

        enter_person.setFirstName(request.getFirstName());
        enter_person.setLastName(request.getLastName());
        enter_person.setFatherName(request.getFatherName());
        enter_person.setTelephone(request.getTelephone());

        rep.save(enter_person);
    }

    @PostMapping(path = "/CourierProfileFirst")
    public Courier CourierProfileFirst (@RequestBody Person request) {
        Courier new_courier = new Courier();
        Person enter_person = rep.findByLogin(request.getLogin());

        enter_person.setFirstName(request.getFirstName());
        enter_person.setLastName(request.getLastName());
        enter_person.setFatherName(request.getFatherName());
        enter_person.setTelephone(request.getTelephone());
        enter_person.setIs_courier(true);
        new_courier.setCid(enter_person.getPid());

        rep.save(enter_person);
        //courier_rep.save(new_courier);
        return new_courier;
    }

    @PostMapping(path = "/CourierProfileSecond")
    public void CourierProfileSecond (@RequestBody Courier request){
        Courier new_courier = new Courier();

        new_courier.setCid(request.getCid());
        new_courier.setAppealCount(request.getAppealCount());
        new_courier.setBirthDate(request.getBirthDate());
        new_courier.setMarkCount(0);
        new_courier.setMarkSum(0.0);
        new_courier.setMotionType(request.getMotionType());
        new_courier.setPassportSerias(request.getPassportSerias());
        new_courier.setPassportNum(request.getPassportNum());
        new_courier.setRating(0.0);
        new_courier.setWarningCount(0);

        courier_rep.save(new_courier);
    }

    @GetMapping(path = "/GetOrderTemplate")
    public Ord GetOrderTemplate () {
        Ord order = new Ord();
        return order;
    }

    @PostMapping(path = "/CreateOrder")
    public void CreateOrder (@RequestBody Ord request){ //в оредере уже должен содержаться id клиента, создающего заказ
        Ord order = new Ord();

        order.setCost(request.getCost());
        order.setClientId(request.getClientId());
        order.setStartAddress(request.getStartAddress());
        order.setEndAddress(request.getEndAddress());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setDeliveryObj(request.getDeliveryObj());
        order.setDeliveryTime(request.getDeliveryTime());
        order.setDeliveryType(request.getDeliveryType());
        order.setStartDate(request.getStartDate());
        order.setStatus("Активен");

        order_rep.save(order);
    }

    @PostMapping(path = "/CancelOrder")
    public void CancelOrder (@RequestBody String id) {
        Ord order = new Ord ();
        long oid = Long.parseLong(id);
        order = order_rep.findByOid(oid);
        order.setStatus("Отменен");
        order_rep.save(order);
    }

    @PostMapping(path = "/NewMark")
    public void NewMark (@RequestBody String mark) {
        String[] info = mark.split(",");
        Double new_mark = Double.parseDouble(info[1]);
        Courier new_courier = courier_rep.findByCid(Long.parseLong(info[0]));

        Double markSum = new_courier.getMarkSum() + new_mark;
        int markCount = new_courier.getMarkCount() + 1;
        Double rating = markSum/markCount;
        new_courier.setRating(rating);
        new_courier.setMarkCount(markCount);
        new_courier.setMarkSum(markSum);

        if (new_mark == 3.0)
            new_courier.setWarningCount(new_courier.getWarningCount()+1);
        if (new_mark < 3.0)
            new_courier.setAppealCount(new_courier.getAppealCount()+1);
        if (new_courier.getWarningCount() >= 3)
            new_courier.setAppealCount(new_courier.getAppealCount()+1);

        courier_rep.save(new_courier);
        Courier to_block = courier_rep.findByCid(Long.parseLong(info[0]));

        if (to_block.getAppealCount() >= 3)
        {
            Person delete = rep.findByPid(to_block.getCid());
            delete.setIs_courier(false);
            rep.save(delete);
            courier_rep.delete(to_block);
        }
    }

    @PostMapping(path = "/EqualsAddresses")
    public Boolean EqualsAddresses (@RequestBody String aboutOrder) {
        String[] info = aboutOrder.split("#");
        Ord order = order_rep.findByOid(Long.parseLong(info[0]));

        if (order.getEndAddress().equals(info[1]))
            return true;
        else return false;
    }

    @PostMapping(path = "/AllClientOrders")
    public List<Ord> AllClientOrders (@RequestBody String client_id) {
        List<Ord> all_orders = order_rep.findByClientId(Long.parseLong(client_id));
        return all_orders;
    }

    @PostMapping(path = "/AllCourierOrders")
    public List<Ord> AllCourierOrders (@RequestBody String courier_id) {
        List<Ord> all_orders = order_rep.findByCourierId(Long.parseLong(courier_id));
        return all_orders;
    }

    @PostMapping(path = "/SetCourierOnOrder")
    public void SetCourierOnOrder (@RequestBody String order) {
        String[] info = order.split(",");
        Ord ord = new Ord ();
        long oid = Long.parseLong(info[0]);
        ord = order_rep.findByOid(oid);

        ord.setCourierId(Long.parseLong(info[1]));
        order_rep.save(ord);
    }

    @PostMapping(path = "/CancelCourierOnOrder")
    public void CancelCourierOnOrder (@RequestBody String order_id) {
        Ord ord = new Ord ();
        ord = order_rep.findByOid(Long.parseLong(order_id));

        ord.setCourierId(0);
        order_rep.save(ord);
    }

    @PostMapping(path = "/EndOfOrderByCourier")
    public void EndOfOrderByCourier (@RequestBody String order_id) {
        Ord order = new Ord ();
        long oid = Long.parseLong(order_id);
        order = order_rep.findByOid(oid);
        order.setStatus("Ожидает подтверждения клиента");
        order_rep.save(order);
    }

    @PostMapping(path = "/EndOfOrderByClient")
    public void EndOfOrderByClient (@RequestBody String order_id) {
        Ord order = new Ord ();
        long oid = Long.parseLong(order_id);
        order = order_rep.findByOid(oid);
        order.setStatus("Завершен");
        order_rep.save(order);
    }
}
