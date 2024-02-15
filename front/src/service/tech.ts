import { api } from "./api"

export const getAllTechs = async () => {
  const response = await api.get('techs/', {
    headers: {
      'User-Agent': 'insomnia/8.6.1',
    }
  });

  return response;
}