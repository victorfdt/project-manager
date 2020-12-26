import axios from "axios";
import { GET_ERRORS, GET_PROJECT, GET_PROJECTS } from "./Types";

export const createProject = (project, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response != null ? err.response.data : null,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  try {
    const projects = await axios.get("http://localhost:8080/api/project/all");
    dispatch({
      type: GET_PROJECTS,
      payload: projects != null ? projects.data : null,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response != null ? err.response.data : null,
    });
  }
};

export const getProject = (identifier, history) => async (dispatch) => {
  try {
    const project = await axios.get(
      `http://localhost:8080/api/project/${identifier}`
    );
    dispatch({
      type: GET_PROJECT,
      payload: project != null ? project.data : null,
    });
  } catch (err) {
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: err.response != null ? err.response.data : null,
    });
  }
};

export const updateProject = (project, history) => async (dispatch) => {
  try {
    const res = await axios.put("http://localhost:8080/api/project", project);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response != null ? err.response.data : null,
    });
  }
};
