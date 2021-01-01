import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/ProjectActions";
import { clearErrors } from "../../actions/ErrorActions";
import classnames from "classnames";

class CreateProjectForm extends Component {
  constructor(props) {
    super(props);

    // It is the initial state of the component
    this.state = {
      name: "",
      identifier: "",
      description: "",
      startDate: "",
      endDate: "",
      errors: {},
    };

    // Bind the onChange
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  // Life Cycle Hooks
  // It is called when the props changes.
  static getDerivedStateFromProps(props, state) {
    if (props.errors !== state.errors) {
      return {
        errors: props.errors,
      };
    }

    return null;
  }

  componentDidMount() {
    this.props.clearErrors();
  }

  /**
   * It is not possible to change the state directly, so I need to set a new state.
   */
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();

    const newProject = {
      name: this.state.name,
      identifier: this.state.identifier,
      description: this.state.description,
      startDate: this.state.startDate,
      endDate: this.state.endDate,
    };

    this.props.createProject(newProject, this.props.history);
  }

  render() {
    const { errors } = this.state;
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Create Project</h5>
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

// It states that those function are required for this component to work properly
CreateProjectForm.propTypes = {
  createProject: PropTypes.func.isRequired,
  clearErrors: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

//It maps the data from the state, which is handled by redux, into the props of the container
const mapStateToProps = (state) => ({
  errors: state.errors,
});

// It maps the action's methods and make them available at the props
export default connect(mapStateToProps, { createProject, clearErrors })(
  CreateProjectForm
);
