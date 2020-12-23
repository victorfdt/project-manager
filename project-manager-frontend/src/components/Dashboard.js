import React, { Component } from "react";
import CreateProjectButton from "./project/CreateProjectButton";
import ProjectItem from "./project/ProjectItem";
import { getProjects } from "../actions/ProjectActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }

  render() {
    const { projects } = this.props.projectReducer;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />

              {projects.map((project) => (
                <ProjectItem key={project.id} project={project} />
              ))}

              {projects.length == 0 && (
                <div className="alert alert-info">
                  <strong>Info!</strong> There are no projects.
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  projectReducer: state.projectReducer,
});

Dashboard.propTypes = {
  projectReducer: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired,
};

export default connect(mapStateToProps, { getProjects })(Dashboard);
