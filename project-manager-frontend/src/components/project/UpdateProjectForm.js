import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { getProject, updateProject } from "../../actions/ProjectActions";
import { clearErrors } from "../../actions/ErrorActions";
import classnames from "classnames";

class UpdateProjectForm extends Component {
  constructor(props) {
    super(props);

    // Initial state
    this.state = {
      id: "",
      name: "",
      identifier: "",
      description: "",
      startDate: "",
      endDate: "",
      errors: [],
    };

    // Bind the onChange
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();

    const newProject = {
      id: this.state.id,
      name: this.state.name,
      identifier: this.state.identifier,
      description: this.state.description,
      startDate: this.state.startDate,
      endDate: this.state.endDate,
    };

    this.props.updateProject(newProject, this.props.history);
  }

  componentDidMount() {
    // Get the id that was passed by param
    const { identifier } = this.props.match.params;

    this.props.clearErrors();

    // Make the request to get the project
    this.props.getProject(identifier, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }

    const {
      id,
      name,
      identifier,
      description,
      startDate,
      endDate,
    } = nextProps.project;

    this.setState({
      id,
      name,
      identifier,
      description,
      startDate,
      endDate,
    });
  }

  render() {
    const { errors } = this.state;
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Update Project</h5>
              <hr />
              <form onSubmit={this.onSubmit}>
                {/** Project name */}
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.name,
                    })}
                    placeholder="Project Name"
                    name="name"
                    value={this.state.name}
                    onChange={this.onChange}
                  />

                  {errors.name && (
                    <div className="invalid-feedback">{errors.name}</div>
                  )}
                </div>

                {/** Project Identifier */}
                <div className="form-group">
                  <input
                    type="text"
                    disabled
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.identifier || errors.message,
                    })}
                    placeholder="Unique Project ID"
                    name="identifier"
                    value={this.state.identifier}
                    onChange={this.onChange}
                  />
                  {(errors.identifier || errors.message) && (
                    <div className="invalid-feedback">
                      {errors.identifier}
                      {errors.message}
                    </div>
                  )}
                </div>

                {/** Project Description */}
                <div className="form-group">
                  <textarea
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.description,
                    })}
                    placeholder="Project Description"
                    name="description"
                    value={this.state.description}
                    onChange={this.onChange}
                  ></textarea>
                  {errors.description && (
                    <div className="invalid-feedback">{errors.description}</div>
                  )}
                </div>

                {/** Project Start Date */}
                <h6>Start Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="startDate"
                    value={this.state.startDate}
                    onChange={this.onChange}
                  />
                </div>

                {/** Project End Date */}
                <h6>Estimated End Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="endDate"
                    value={this.state.endDate}
                    onChange={this.onChange}
                  />
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  project: state.projectReducer.project,
  getProject: state.getProject,
  errors: state.errors,
});

UpdateProjectForm.propTypes = {
  getProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

export default connect(mapStateToProps, {
  getProject,
  updateProject,
  clearErrors,
})(UpdateProjectForm);
