package com.fh.util;

import com.elab.core.serialization.SerializeFactory;
import com.fh.po.EnumerationMode;
import com.fh.po.EnumerationModes;
import com.fh.po.ValueMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufeng on 2015/10/13.
 */
public class ConfigHelper {


    public static Map<String,List<ValueMode>> enumerationMap;
    public static Map<String,ValueMode> valueModeMap;

    static{
        //枚举
        enumerationMap = new HashMap<String,List<ValueMode>>();
        valueModeMap = new HashMap<String,ValueMode>();

        EnumerationModes modes = SerializeFactory.getXmlSerializer().FromFile(Constants.FILEPATH_ENUMERATION, EnumerationModes.class);
        List<EnumerationMode> list =  modes.getEnumeration();
        if(list != null){
            for(EnumerationMode mode : list){
                String type = mode.getType();
                List<ValueMode> values = mode.getValues();
                enumerationMap.put(type,values);
                for(ValueMode vm : values){
                    valueModeMap.put(type+vm.getVal(),vm);
                }
            }
        }
    }

    public static String getName(String type,String code){
        ValueMode vm = valueModeMap.get(type + code);
        if(vm != null) return vm.getName();
        return "";
    }

    public static boolean isOutOfRange(String type, String value){
        if(!StringUtils.areNotEmpty(type,value)){
            return true;
        }

        List<ValueMode> vm  = ConfigHelper.enumerationMap.get(type);

        if(null == vm || vm.isEmpty()){
            return true;
        }

        for(ValueMode item: vm){
            if(item.getVal().equals(value)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        List<ValueMode> valueModeList = enumerationMap.get("10002");
        for(ValueMode vm : valueModeList){
            System.out.println(vm.getName()+":"+vm.getVal());
        }
    }
}
