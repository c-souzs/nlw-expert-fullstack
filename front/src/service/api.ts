import axios, { AxiosInstance, AxiosRequestConfig } from "axios";

const baseURL = 'http://localhost:8085/api/';

const config: AxiosRequestConfig = {
  baseURL: baseURL,
  validateStatus: function (status) {
    return status >= 200 && status <= 302
  },
}

export const api: AxiosInstance = axios.create(config)
