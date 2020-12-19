import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/layout/Header";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CreateProjectForm from "./components/project/CreateProjectForm";

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Route path="/dashboard" component={Dashboard} />
        <Route path="/addProject" component={CreateProjectForm} />
      </div>
    </Router>
  );
}

export default App;
