package com.myspring.lim101.utils;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

public class PubMap extends EgovMap {

    private static final Logger logger = LoggerFactory.getLogger(PubMap.class);
    private static final long serialVersionUID = 77303327953688671L;


	/*
	public PubMap getMap() {
		return this;
	}

	public void setMap(PubMap map) {
		this.clear();
		this.putAll(map);
	}

*/


    public PubMap getFormMap() {
        return this;
    }
    public void setFormMap(PubMap formMap) {
        this.clear();
        this.putAll(formMap);
    }

    public PubMap getVoMap() {
        return this;
    }

    public void setVoMap(PubMap voMap) {
        this.clear();
        this.putAll(voMap);
    }

    @Override
    public Object put(Object key, Object value) {
        if(key == null || key.toString().trim().length() < 1){
            return this;
        }
        // clob to string
        if(value != null && value instanceof java.sql.Clob ) {
            super.put(key, getStringForClob( (Clob) value));
            return this;
        }
        // blob to string
        if(value != null && value instanceof java.sql.Blob ) {
            super.put(key, getStringForBlob( (Blob) value));
            return this;
        }

        if(value == null){
            value = "";
        }
        super.put(key, value);
        return this;

    }

    public void set(Object key, Object value) {
        // clob to string
        if(value != null && value instanceof java.sql.Clob ) {
            super.put(key, getStringForClob( (Clob) value));
            return;
        }
        // blob to string
        if(value != null && value instanceof java.sql.Blob ) {
            super.put(key, getStringForBlob( (Blob) value));
            return;
        }
        super.put(key, value);
    }

    /**
     * 키 변경없이 값 추가
     * @param key
     * @param value
     */
    public void setNoCamel(Object key, Object value) {
        int index = this.size();
        super.put(index, key, value);
    }

    /**
     * 키 변경없이 값 추가
     * @param key
     * @param value
     */
    public void putNoCamel(Object key, Object value) {
        int index = this.size();
        super.put(index, key, value);
    }

    public void setInt(Object obj, int i) {
        Integer integer = new Integer(i);
        super.put(obj, integer);
    }
    public void setLong(Object obj, long l) {
        Long ll = new Long(l);
        super.put(obj, ll);
    }

    public void setDouble(Object obj, double d) {
        Double double1 = new Double(d);
        super.put(obj, double1);
    }

    public String getString(Object obj) {
        Object obj1 = super.get(obj);
        if (obj1 == null) {
            return "";
        } else {
            return obj1.toString();
        }
    }

    public int getInt(Object obj) {
        Object obj1 = super.get(obj);
        if (obj1 == null) {
            return 0;
        } else {
            if (obj1 instanceof Number) {
                return ((Number) obj1).intValue();
            }
            if (obj1 instanceof String){
                try {
                    return Integer.parseInt(obj1.toString());
                } catch (NumberFormatException e) {
                    logger.error(getMethodName(), e);
                } catch (Exception e) {
                    logger.error(getMethodName(), e);
                }
            }
        }
        return 0;
    }
    public long getLong(Object obj) {
        Object obj1 = super.get(obj);
        if (obj1 == null) {
            return 0L;
        } else {
            if (obj1 instanceof Number) {
                return ((Number) obj1).longValue();
            }
            if (obj1 instanceof String) {
                try {
                    return Long.parseLong(obj1.toString());
                } catch (NumberFormatException e) {
                    logger.error(getMethodName(), e);
                } catch (Exception e) {
                    logger.error(getMethodName(), e);
                }
            }
        }
        return 0L;
    }

    public double getDouble(Object obj) {
        Object obj1 = super.get(obj);
        if (obj1 == null) {
            return 0.0D;
        } else {
            if (obj1 instanceof Number) {
                return ((Number) obj1).doubleValue();
            }
            if (obj1 instanceof String) {
                try {
                    return Double.parseDouble(obj1.toString());
                } catch (NumberFormatException e) {
                    logger.error(getMethodName(), e);
                } catch (Exception e) {
                    logger.error(getMethodName(), e);
                }
            }
        }
        return 0.0D;
    }

    @Override
    public Object get(Object key) {
        if(!this.containsKey(key))
            return "";

        return super.get(key);
    }



    /**
     * java.sql.Clob 을 String 으로 리텅
     * @param clob
     * @return
     */
    private String getStringForClob(Clob clob) {

        StringBuilder sbf = new StringBuilder();
        Reader br = null;
        char[] buf = new char[1024];
        int readcnt;
        try {
            if(clob != null){
                br = clob.getCharacterStream();
                while ((readcnt=br.read(buf,0,1024))!=-1) {
                    sbf.append(buf,0,readcnt);
                }
            }

        } catch (SQLException e) {
            logger.error(getMethodName(), e);
        } catch (IOException e) {
            logger.error(getMethodName(), e);
        }finally{
            if(br!=null)
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(getMethodName(), e);
                }
        }
        return sbf.toString();
    }


    private String getStringForBlob(Blob blob) {

        String aux;
        StringBuffer strOut = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(blob.getBinaryStream()));
            while ((aux=br.readLine())!=null) {
                strOut.append(aux);
            }
            return strOut.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(getMethodName(), e);
                }
            }
        }
        return strOut.toString();
    }

    public String getMethodName() {
        String name = "";

        if(Thread.currentThread() != null
                && 	Thread.currentThread().getStackTrace() != null
                && 	Thread.currentThread().getStackTrace().length >= 2
        ) {
            name = Thread.currentThread().getStackTrace()[1].getMethodName();
        }
        return name;
    }
}
