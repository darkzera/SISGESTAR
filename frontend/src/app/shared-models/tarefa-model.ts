import { TarefaComentariosModalComponent } from "../admin/tarefa/tarefa-list/tarefa-comentario/tarefa-comentarios.component";
import { ComentariosListModel } from "./comentarioslist-model";

export class TarefaModel {
    constructor(
    public id?: number,
    public titulo?: string,
    public descricao?: string, 
    public data_abertura?: string,
    public idResponsavel?: number,
    public comentarios?: ComentariosListModel[]
    ){}
}