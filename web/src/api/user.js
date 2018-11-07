import $http from '@/libs/axios';

// export const login = (params) => $http.get('api/login', { params: { ...params } });
export const login = (params) => $http.post('api/admin/login', { ...params });
