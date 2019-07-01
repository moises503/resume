# Resume App

This is a Moises's resume app developed with CLEAN software architecture. App includes four layers: app, presentation, domain and data.  

## Libraries

Dagger 2, Retrofit 2, GSON parser, RxJava, RxAndroid and mockito

### App layer

This layer only include android framework classes like activities and adapters. Usually here dagger 2 is used for dependencies injection. Resume package include two sub packages, di and adapters. Di has a module and component for Dagger 2. Adapters package include a recycler view adapter.

### Presentation layer 

This layer only include presenters and view interfaces, normally it is a bridge between app and domain layer. Resume package has presenter definition, view definition and presenter implementation

### Domain layer

Here only exists classes for business logic, the datasources and repositories are defined but not implemented. Usually here, it's defined method for make a use case like Rx Single Object for observe in presenter. Resume package includes these classes.

### Data layer

Here are define datasources and repositories, in this layer use Retrofit 2 annotations for parse web service responses and web service requests to data classes, for transform these to domain classes, are need a mapper object. Resume package include these classes.