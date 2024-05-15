# 2-1 OOP Project

**Members** :

- [Talha Jubair Siam](https://github.com/tsensei/)
- [Nafiul Alim Adeeb](https://github.com/jon0iko)
- [Sharmila Surovi Pushpita](https://github.com/Sharmila-pushpita)

## Contributing Guidelines

1. Clone the project

   ```
   git clone git@github.com:tsensei/2-1_OOP_Project.git
   ```

2. Do a check pull to ensure local/main is in sync with remote/main:

   ```
   git pull origin main
   ```

3. Install all dependencies

Intellij will install all the packages for you

4. Create a feature branch for new changes:

   ```
   git checkout -b [new-branch-name]
   ```

5. Before pushing your changes, sync with the latest main branch:

- Before pushing your work, check if there have been any new commits in the remote main branch since you created your feature branch.

- If there are new changes:

  5.1 : Switch to your local `main` branch:

  ```
  git checkout main
  ```

  5.2 : Pull the latest changes from the remote `main` branch:

  ```
  git pull origin main
  ```

  5.3 : Switch back to your feature branch:

  ```
  git checkout [feature-branch-name]
  ```

  5.4 : Merge the latest `main` into your feature branch:

  ```
  git merge main
  ```

6. Add, commit and push changes:

   ```
   git add .
   git commit -m "commit message"
   git push origin [new-branch-name]
   ```

7. Create a PR in github.

8. Merge / wait for PR to merge.

9. Switch to local main branch:

   ```
   git checkout main
   ```

10. Update local `main` branch:

    ```
    git pull origin main
    ```

11. Delete local feature branch and prune remote tracking branch:

    ```
    git branch -d [feature-branch-name]
    git fetch --prune
    ```

12. Repeat from step 2 for making new changes.
