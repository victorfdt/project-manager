import React, { Component } from "react";
import CreateProjectButton from "./project/CreateProjectButton";
import ProjectItem from "./project/ProjectItem";
import { getProjects } from "../actions/ProjectActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";

class Dashboard extends Component {
  constructor(props) {
    super(props);

    this.state = { errors: {} };
  }

  componentDidMount() {
    this.setState({
      errors: [],
    });

    this.props.getProjects();
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  render() {
    const { projects } = this.props.projectReducer;
    const { errors } = this.props.errors;

    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              {this.state.errors.length > 0 && (
                <div className="alert alert-info">
                  {this.state.errors.message}
                </div>
              )}
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              {projects.map((project) => (
                <ProjectItem
                  key={project.id}
                  project={project}
                  showDeleteModal={this.handleShowDeleteModal}
                />
              ))}
              {projects.length == 0 && (
                <div className="alert alert-info">There are no projects.</div>
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
  errors: state.errors,
});

Dashboard.propTypes = {
  projectReducer: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

export default connect(mapStateToProps, { getProjects })(Dashboard);
