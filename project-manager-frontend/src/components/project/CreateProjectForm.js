import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/ProjectActions";

class CreateProjectForm extends Component {
  constructor() {
    super();

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
  // I will call this method when the props change.
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
    console.log(this.props.errors);
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
              <h5 className="display-4 text-center">Create Project form</h5>
              <hr />
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg "
                    placeholder="Project Name"
                    name="name"
                    value={this.state.name}
                    onChange={this.onChange}
                  />
                  <p>{errors.name}</p>
                </div>

                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Unique Project ID"
                    name="identifier"
                    value={this.state.identifier}
                    onChange={this.onChange}
                  />
                  <p>{errors.identifier}</p>
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Project Description"
                    name="description"
                    value={this.state.description}
                    onChange={this.onChange}
                  ></textarea>
                  <p>{errors.description}</p>
                </div>
                <h6>Start Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="startDate"
                    value={this.state.startDate}
                    onChange={this.onChange}
                  />
                  <p>{errors.startDate}</p>
                </div>
                <h6>Estimated End Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="endDate"
                    value={this.state.endDate}
                    onChange={this.onChange}
                  />
                  <p>{errors.endDate}</p>
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
  errors: PropTypes.object.isRequired,
};

//It maps the data from the state, which is handled by redux, into the props of the container
const mapStateToProps = (state) => ({
  errors: state.errors,
});

// It maps the action's methods and make them available at the props
export default connect(mapStateToProps, { createProject })(CreateProjectForm);
