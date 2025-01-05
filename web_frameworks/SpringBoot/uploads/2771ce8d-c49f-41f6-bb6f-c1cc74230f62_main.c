#include <stdio.h>
#include <stdlib.h>
#include <string.h>
const int MAX = 100;
void showMenu(void);
// 1. them sv: them id moi, them fName, them lName, them yob, them gpa
void addStudent(char id[][MAX], char fName[][MAX],
                char lName[][MAX], int yob[],
                float gpa[], int *size);
// 5. in ra danh sach sv
void printStudentList(char id[][MAX], char fName[][MAX],
                      char lName[][MAX], int yob[],
                      float gpa[], int size);
// 2. tim kiem sinh vien dua tren id
int findIndexById(char id[][MAX], int size, char key[]); // tìm chuỗi key
void printStudent(char id[][MAX], char fName[][MAX],
                  char lName[][MAX], int yob[],
                  float gpa[], int pos);
void removeStudentByPos(char id[][MAX], char fName[][MAX],
                        char lName[][MAX], int yob[],
                        float gpa[], int *size, int pos);
int main()
{
    char id[50][MAX];
    char fName[50][MAX];
    char lName[50][MAX];
    // yob dùng mảng 1 chiều
    int yob[50];
    float gpa[50];

    int size = 0; // tạo size dùng chung cho cả danh sách
                  // mảng chưa có gì cả

    int choice;
    char buffer;
    do
    {
        // in ra menu
        showMenu();
        // xin lựa chọn
        // tránh trường hợp người dùng nhập tào lao
        do
        {
            printf("\nPlease, input your choice(integer): ");
            scanf("%d", &choice);
            scanf("%c", &buffer);
            if (buffer != 10)
            {
                printf("\nDo u know input integer number :");
            }
        } while (buffer != 10);
        // xử lí choice

        switch (choice)
        {
        case 1:
        {
            printf("\nAdd New Student ");
            addStudent(id, fName, lName, yob, gpa, &size);
            break;
        }
        case 2:
        {
            printf("\nSearch infor student ");
            char key[MAX];
            printf("\nInput Id: ");
            gets(key);
            fflush(stdin);
            int pos = findIndexById(id, size, key);
            if (pos == -1)
            {
                printf("\nStudent doesn't exist");
            }
            else
            {
                printStudent(id, fName, lName, yob,
                             gpa, pos);
            }
            break;
        }
        case 3:
        {
            printf("\nDelete infor student ");
            char key[MAX];
            printf("\nInput Id: ");
            gets(key);
            fflush(stdin);
            int pos = findIndexById(id, size, key);
            if (pos == -1)
            {
                printf("\nStudent doesn't exist");
            }
            else
            {
                printf("\nStudent deleted successfully ! ");
                printStudent(id, fName, lName, yob,
                             gpa, pos);
                removeStudentByPos(id, fName, lName, yob, gpa, &size, pos);
            }
            break;
        }
        case 4:
        {
            printf("\nhello 4: ");
            break;
        }
        case 5:
        {
            printf("\nPrint Student List ");
            printStudentList(id, fName, lName, yob, gpa, size);
            break;
        }
        case 6:
        {
            printf("\nhello 6: ");
            break;
        }
        case 7:
        {
            printf("\nHen gap lai, song tot nhe anh ban");
            break;
        }
        default:
        {
            printf("\nYour choice is required between 1 and 7");
            break;
        }
        }
    } while (choice != 7);
    return 0;
}
void showMenu(void)
{
    printf("\n==============================");
    printf("\nStudent Management Application");
    printf("\n1. Add a new student");
    printf("\n2. Search student by id");
    printf("\n3. Delete student by id");
    printf("\n4. Export highest gpa list");
    printf("\n5. Print student list");
    printf("\n6. Sort student list by fName ascending");
    printf("\n7. Quit");
}
void addStudent(char id[][MAX], char fName[][MAX],
                char lName[][MAX], int yob[],
                float gpa[], int *size)
{
    printf("\nId   : ");
    gets(id[*size]);
    fflush(stdin);

    printf("\nfName: ");
    gets(fName[*size]);
    fflush(stdin);

    printf("\nlName: ");
    gets(lName[*size]);
    fflush(stdin);

    printf("\nYob  : ");
    scanf("%d", &yob[*size]);
    fflush(stdin);

    printf("\ngpa  : ");
    scanf("%f", &gpa[*size]);
    fflush(stdin);

    (*size)++;

    printf("\nAdding Successfully !"); // lừa người dùng

    printf("\n*size = %d", *size);
}
void printStudentList(char id[][MAX], char fName[][MAX],
                      char lName[][MAX], int yob[],
                      float gpa[], int size)
{
    if (size == 0)
    {
        printf("\nNothing to print");
    }
    else
    {
        for (int i = 0; i <= size - 1; i++)
        {
            printf("\n %-9s | %-9s | %-9s | %4d | %5.2f",
                   id[i], fName[i], lName[i], yob[i], gpa[i]);
        }
    }
}
int findIndexById(char id[][MAX], int size, char key[])
{
    for (int i = 0; i <= size - 1; i++)
    {
        if (strcmp(id[i], key) == 0)
            return 1;
    }
    return -1;
}
void printStudent(char id[][MAX], char fName[][MAX],
                  char lName[][MAX], int yob[],
                  float gpa[], int pos)
{

    printf("\n %-9s | %-9s | %-9s | %4d | %5.2f",
           id[pos], fName[pos], lName[pos], yob[pos], gpa[pos]);
}
void removeStudentByPos(char id[][MAX], char fName[][MAX],
                        char lName[][MAX], int yob[],
                        float gpa[], int *size, int pos)
{
    for (int i = pos; i <= *size - 1; i++)
    {
        strcpy(id[i], id[i + 1]);
        strcpy(fName[i], fName[i + 1]);
        strcpy(lName[i], lName[i + 1]);
        yob[i] = yob[i + 1];
        gpa[i] = gpa[i + 1];
    }
    (*size)--;
}
