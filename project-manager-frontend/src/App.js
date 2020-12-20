import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/layout/Header";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CreateProjectForm from "./components/project/CreateProjectForm";
import { Provider } from "react-redux";
import Store from "./store";

function App() {
  return (
    <Provider store={Store}>
      <Router>
        <div className="App">
          <Header />
          <Route path="/dashboard" component={Dashboard} />
          <Route path="/addProject" component={CreateProjectForm} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
