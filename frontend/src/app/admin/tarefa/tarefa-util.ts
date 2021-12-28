import {GenericTableColumn} from '../../shared/models/generic-table-column';
import {GenericTableButton} from '../../shared/models/generic-table-button';
import {MessageUtil} from '../../utils/message-util';
import {GenericTableFilter} from '../../shared/models/generic-table-filter';

export class TarefaUtil {
    static COLUMNS: GenericTableColumn[] = [
        { field: 'nome', header: 'nome' },
        { field: 'id', header: 'id' },
    ];

    static FILTERS: GenericTableFilter[] = [
        {
            field: 'descricao',
            type: 'string',
            label: MessageUtil.LABELS_FILTER
        }
    ];
}
