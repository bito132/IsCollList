module br.com.iscolllist {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires  java.naming;


    opens br.com.iscolllist to javafx.fxml;
    opens br.com.iscolllist.entities to org.hibernate.orm.core;
    exports br.com.iscolllist;
    exports br.com.iscolllist.controllers;
    opens br.com.iscolllist.controllers to javafx.fxml;
}