package cn.wugou.dao;

import lombok.Data;

import javax.sql.rowset.spi.SyncResolver;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
@Data
public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbys;
    private Map<String,String> card;
    private Set<String> games;
    private String wife;
    private Properties info;
}
