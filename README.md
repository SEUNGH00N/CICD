# ShoonCICD

This project demonstrates a CI/CD pipeline setup using GitHub Actions and follows the MVC architectural pattern.

## CI/CD Pipeline

The CI/CD (Continuous Integration and Continuous Deployment) pipeline is a method to frequently deliver applications to customers by introducing automation into the stages of app development. The main concepts are continuous integration, continuous deployment, and continuous delivery.

### Continuous Integration (CI)

- Developers frequently commit code to the version control repository.
- Automated builds and tests are run to ensure new changes do not break the application.
- Early detection of issues reduces integration problems and allows for faster fixes.

### Continuous Delivery (CD)

- Builds that pass CI are automatically deployed to a staging environment.
- Ensures that the codebase is always in a deployable state.
- Manual approval may be required before deployment to production.

### Continuous Deployment (CD)

- Extends continuous delivery by automating the release of new changes to production.
- Every change that passes automated tests is automatically deployed to production without manual intervention.
- Allows for rapid and reliable delivery of new features and fixes to users.

### CI/CD Pipeline Steps

1. **Code**: Developers write and commit code.
2. **Build**: The code is compiled and built.
3. **Test**: Automated tests are run to ensure functionality.
4. **Deploy**: The application is deployed to a staging or production environment.
5. **Monitor**: The application is monitored to ensure performance and stability.


### MVC 아키텍처
모델-뷰-컨트롤러(MVC) 아키텍처는 애플리케이션을 세 가지 상호 연결된 구성 요소로 나누는 소프트웨어 설계 패턴입니다.

### 모델
애플리케이션의 데이터, 로직 및 규칙을 관리합니다.
데이터를 직접 관리하고 데이터베이스 로직을 처리합니다.
데이터에 변경 사항이 있을 때 뷰에 알립니다.
### 뷰
애플리케이션의 UI를 나타냅니다.
사용자에게 데이터를 표시하고 사용자 명령을 컨트롤러에 전달합니다.
프레젠테이션 레이어를 담당합니다.
### 컨트롤러
모델과 뷰 사이의 중개자로 작동합니다.
뷰로부터 사용자 입력을 받아 이를 처리하고(종종 모델에 적합한 형식으로 변환) 모델에 보냅니다.
모델로부터 새로운 데이터를 받아 뷰를 업데이트합니다.
### MVC의 장점
관심사의 분리: 각 구성 요소가 특정 책임을 가지므로 코드베이스 관리 및 확장이 용이합니다.
재사용성: 구성 요소를 애플리케이션 전체에서 재사용할 수 있습니다.
유지보수성: 명확한 구성 요소 분리로 인해 유지보수 및 테스트가 용이합니다.

### GitHub Actions Workflow

```yaml
name: shoonCICD

on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v2

      - name: Setup Node.js
        uses: actions/setup-node@v2
        with:
          node-version: '20'

      - name: Install Dependencies
        run: npm install

      - name: Run Tests
        run: npm test

MVC Architecture
The Model-View-Controller (MVC) architecture is a software design pattern that divides an application into three interconnected components:

Model
Manages the data, logic, and rules of the application.
Directly manages the data and database logic.
Notifies the view when there is a change in data.
View
Represents the UI of the application.
Displays data to the user and sends user commands to the controller.
It’s responsible for the presentation layer.
Controller
Acts as an intermediary between the model and view.
Takes user input from the view, processes it (often transforming into a format suitable for the model), and then sends it to the model.
Updates the view with new data from the model.
Benefits of MVC
Separation of Concerns: Each component has a specific responsibility, making the codebase easier to manage and scale.
Reusability: Components can be reused across the application.
Maintainability: Easier to maintain and test due to the clear separation of components.


