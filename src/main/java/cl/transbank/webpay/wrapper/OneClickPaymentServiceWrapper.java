package cl.transbank.webpay.wrapper;

import cl.transbank.webpay.Webpay;
import cl.transbank.webpay.security.SoapSignature;
import com.transbank.webpayserver.webservices.*;

public class OneClickPaymentServiceWrapper extends ServiceWrapperBase {

    private OneClickPaymentService port;

    @Override
    protected String getWsdlName() {
        return "transbank-oneclick-payment-service.wsdl";
    }

    protected OneClickPaymentServiceWrapper(Webpay.Environment environment, SoapSignature signature) throws Exception {
        super(environment, signature);
        this.port = new OneClickPaymentServiceImplService(getWsdlUrl()).getOneClickPaymentServiceImplPort();
        initPort(port);
    }

    public OneClickInscriptionOutput initInscription(OneClickInscriptionInput input){
        return port.initInscription(input);
    }

    public OneClickFinishInscriptionOutput finishInscription(OneClickFinishInscriptionInput input){
        return port.finishInscription(input);
    }

    public OneClickPayOutput authorize(OneClickPayInput input){
        return port.authorize(input);
    }

    public OneClickReverseOutput codeReverseOneClick(OneClickReverseInput input){
        return port.codeReverseOneClick(input);
    }

    public boolean reverseTransaction(OneClickReverseInput input){
        return port.reverse(input);
    }

    public boolean removeUser(OneClickRemoveUserInput input){
        return port.removeUser(input);
    }

}
