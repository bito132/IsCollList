module br.com.iscolllist {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens br.com.iscolllist to javafx.fxml;
    opens br.com.iscolllist.entities to org.hibernate.orm.core, javafx.fxml;
    exports br.com.iscolllist;
    exports br.com.iscolllist.controllers;
    exports br.com.iscolllist.entities;
    opens br.com.iscolllist.controllers to javafx.fxml;
}