import React, { Component } from "react";
import { Link } from "react-router-dom";
import DeleteProjectModal from "./DeleteProjectModal";

class ProjectItem extends Component {
  constructor(props) {
    super(props);

    this.state = { showDeleteModal: false };

    this.handleShowDeleteModal = this.handleShowDeleteModal.bind(this);
    this.handleHideDeleteModal = this.handleHideDeleteModal.bind(this);
  }

  handleShowDeleteModal = () => {
    this.setState({
      showDeleteModal: true,
    });
  };

  handleHideDeleteModal = () => {
    alert("hide");
    this.setState({
      showDeleteModal: false,
    });
  };

  render() {
    const { project } = this.props;
    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            {/** Identifier */}
            <div className="col-2">
              <span className="mx-auto">{project.identifier}</span>
            </div>

            {/** Name and Description */}
            <div className="col-lg-6 col-md-4 col-8">
              <h3>{project.name}</h3>
              <p>{project.description}</p>
            </div>

            <div className="col-md-4 d-none d-lg-block">
              <ul className="list-group">
                {/** Project Board */}
                <Link to={`/updateProject/${project.identifier}`}>
                  <li className="list-group-item board">
                    <i className="fa fa-flag-checkered pr-1"> Project Board </i>
                  </li>
                </Link>

                {/** Update project */}
                <Link to={`/updateProject/${project.identifier}`}>
                  <li className="list-group-item update">
                    <i className="fa fa-edit pr-1"> Update Project Info</i>
                  </li>
                </Link>
                {/** Delete project */}
                <a href="#" onClick={this.handleShowDeleteModal}>
                  <li className="list-group-item delete">
                    <i className="fa fa-minus-circle pr-1"> Delete Project</i>
                  </li>
                </a>
              </ul>
            </div>
          </div>
        </div>
        <DeleteProjectModal
          showModal={this.state.showDeleteModal}
          hideModal={this.handleHideDeleteModal}
          project={project}
        ></DeleteProjectModal>
      </div>
    );
  }
}

export default ProjectItem;
