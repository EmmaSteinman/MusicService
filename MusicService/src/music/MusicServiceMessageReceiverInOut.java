/**
 * MusicServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package music;


/**
 *  MusicServiceMessageReceiverInOut message receiver
 */
public class MusicServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            MusicServiceSkeleton skel = (MusicServiceSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("getByComposer".equals(methodName)) {
                    music.Result result10 = null;
                    music.ComposerRequest wrappedParam = (music.ComposerRequest) fromOM(msgContext.getEnvelope()
                                                                                                  .getBody()
                                                                                                  .getFirstElement(),
                            music.ComposerRequest.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    result10 = skel.getByComposer(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext), result10,
                            false,
                            new javax.xml.namespace.QName("urn:Music",
                                "getByComposer"));
                } else if ("getByDisc".equals(methodName)) {
                    music.Result result12 = null;
                    music.DiscRequest wrappedParam = (music.DiscRequest) fromOM(msgContext.getEnvelope()
                                                                                          .getBody()
                                                                                          .getFirstElement(),
                            music.DiscRequest.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    result12 = skel.getByDisc(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext), result12,
                            false,
                            new javax.xml.namespace.QName("urn:Music",
                                "getByDisc"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (FaultMessage e) {
            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,
                "faultElement");

            org.apache.axis2.AxisFault f = createAxisFault(e);

            if (e.getFaultMessage() != null) {
                f.setDetail(toOM(e.getFaultMessage(), false));
            }

            throw f;
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(music.ComposerRequest param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.ComposerRequest.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(music.Result param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.Result.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(music.FaultElement param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.FaultElement.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(music.DiscRequest param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.DiscRequest.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory, music.Result param,
        boolean optimizeContent, javax.xml.namespace.QName methodQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(music.Result.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private music.Result wrapgetByComposer() {
        music.Result wrappedElement = new music.Result();

        return wrappedElement;
    }

    private music.Result wrapgetByDisc() {
        music.Result wrappedElement = new music.Result();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type, java.util.Map extraNamespaces)
        throws org.apache.axis2.AxisFault {
        try {
            if (music.ComposerRequest.class.equals(type)) {
                return music.ComposerRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (music.DiscRequest.class.equals(type)) {
                return music.DiscRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (music.FaultElement.class.equals(type)) {
                return music.FaultElement.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (music.Result.class.equals(type)) {
                return music.Result.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(
        org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();

        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }

        return returnMap;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
