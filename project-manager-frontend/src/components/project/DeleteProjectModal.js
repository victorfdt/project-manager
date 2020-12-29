import React, { Component } from "react";
import { deleteProject } from "../../actions/ProjectActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { Modal, Col, Row, Button } from "react-bootstrap";

class DeleteProjectModal extends Component {
  constructor(props) {
    super(props);

    this.handleDeleteProject = this.handleDeleteProject.bind(this);
  }

  handleDeleteProject = (identifier) => {
    this.props.deleteProject(identifier);
  };

  render() {
    return (
      <Modal show={this.props.showModal} onHide={this.props.hideModal}>
        <Modal.Header closeButton>
          <Modal.Title>Delete project {this.props.project.name}</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <Row className="show-grid">
            <Col md={12}>
              <p>
                Do you want to delete the project {this.props.project.name}?
              </p>
            </Col>
          </Row>

          <Row className="show-grid">
            <Col md={6}>
              <Button
                bsStyle="danger"
                bsSize="small"
                onClick={this.props.hideModal}
              >
                No
              </Button>
            </Col>
            <Col md={6} className="text-right">
              <Button
                bsStyle="success"
                bsSize="small"
                onClick={() =>
                  this.handleDeleteProject(this.props.project.identifier)
                }
              >
                Yes
              </Button>
            </Col>
          </Row>
        </Modal.Body>
      </Modal>
    );
  }
}

DeleteProjectModal.propTypes = {
  deleteProject: PropTypes.func.isRequired,
};

export default connect(null, { deleteProject })(DeleteProjectModal);
