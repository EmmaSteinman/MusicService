/**
 * FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package music;

public class FaultMessage extends java.lang.Exception {
    private static final long serialVersionUID = 1552925319110L;
    private music.MusicServiceStub.FaultElement faultMessage;

    public FaultMessage() {
        super("FaultMessage");
    }

    public FaultMessage(java.lang.String s) {
        super(s);
    }

    public FaultMessage(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public FaultMessage(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(music.MusicServiceStub.FaultElement msg) {
        faultMessage = msg;
    }

    public music.MusicServiceStub.FaultElement getFaultMessage() {
        return faultMessage;
    }
}
