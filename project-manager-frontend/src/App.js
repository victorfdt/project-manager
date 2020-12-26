import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/layout/Header";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CreateProjectForm from "./components/project/CreateProjectForm";
import { Provider } from "react-redux";
import Store from "./store";
import MainPage from "./components/layout/MainPage";
import UpdateProjectForm from "./components/project/UpdateProjectForm";

function App() {
  return (
    <Provider store={Store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/" component={MainPage} />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={CreateProjectForm} />
          <Route
            exact
            path="/updateProject/:identifier"
            component={UpdateProjectForm}
          />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
