import $http from '@/libs/axios';

export const login = (params) => $http.post('api/admin/login', { ...params });

export const logout = (params) => $http.post('api/admin/login', { ...params });
