import { DataState } from '../components/enum/DataState';

export interface AppState<T> {
  dataState: DataState;
  data?: T;
  error?: string;
}
