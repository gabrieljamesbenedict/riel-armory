import { Role } from './role.model';

export interface User {
    userId: number;
    name: string;
    password: string;
    role: Role;
}