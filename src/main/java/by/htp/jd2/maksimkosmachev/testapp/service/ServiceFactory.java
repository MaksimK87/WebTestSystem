package by.htp.jd2.maksimkosmachev.testapp.service;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private final ClientService clientService = new ClientServiceImpl();
    private final TestService testService = new TestServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {

        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public TestService getTestService() {

        return testService;
    }

}
